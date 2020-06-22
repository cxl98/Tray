package com.easeArch.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;
import java.util.regex.Pattern;

public class IpUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(IpUtil.class);

    private static final String ANYHOST = "0.0.0.0";
    private static final String LOCALHOST = "127.0.0.1";
    private static final Pattern IP_PATHEND = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");
    private static volatile InetAddress LOCAL_ADDRESS = null;

    // ---------------------- valid ----------------------
    private static boolean isValidAddress(InetAddress address) {
        if (address == null || address.isLoopbackAddress()) {
            return false;
        }
        String name = address.getHostAddress();
        return (name != null
                && !ANYHOST.equals(name)
                && !LOCALHOST.equals(name)
                && IP_PATHEND.matcher(name).matches());
    }

    private static boolean isValidV6Address(Inet6Address address) {
        boolean preferIpv6 = Boolean.getBoolean("java.net.preferIPv6Addresses");
        if (!preferIpv6) {
            return false;
        }
        try {
            return address.isReachable(100);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static InetAddress normalizeV6Address(Inet6Address address) {
        String addr = address.getHostAddress();
        int i = addr.lastIndexOf("%");
        if (i > 0) {
            try {
                return InetAddress.getByName(addr.substring(0, i) + "%" + address.getScopeId());
            } catch (UnknownHostException e) {
                LOGGER.debug("Unknown IPV6 address:", e);
            }
        }
        return address;
    }

    private static InetAddress getLocalAddress0() {
        InetAddress localAddress = null;
        try {
            localAddress = InetAddress.getLocalHost();
            if (localAddress instanceof Inet6Address) {
                Inet6Address address = (Inet6Address) localAddress;
                if (isValidV6Address(address)) {
                    return normalizeV6Address(address);
                } else if (isValidAddress(localAddress)) {
                    return localAddress;
                }
            }
        } catch (UnknownHostException e) {
            LOGGER.error(e.getMessage(), e);
        }

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (null == interfaces) {
                return localAddress;
            }
            while (interfaces.hasMoreElements()) {
                NetworkInterface network = interfaces.nextElement();
                Enumeration<InetAddress> addresses = network.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    if (address instanceof Inet6Address) {
                        Inet6Address v6Address = (Inet6Address) address;
                        if (isValidV6Address(v6Address)) {
                            return normalizeV6Address(v6Address);
                        }
                    } else if (isValidAddress(address)) {
                        return address;
                    }
                }
            }
        } catch (SocketException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return localAddress;
    }

    /**
     * Find first valid IP from local network card
     *
     * @return first valid local IP
     */
    public static InetAddress getLocalAddress() {
        if (LOCAL_ADDRESS != null) {
            return LOCAL_ADDRESS;
        }
        InetAddress localAddress = getLocalAddress0();
        LOCAL_ADDRESS = localAddress;
        return localAddress;
    }

    /**
     * get ip address
     *
     * @return String
     */
    public static String getIp() {
        return getLocalAddress().getHostAddress();
    }

    /**
     * get ip:port
     *
     * @param port
     * @return String
     */
    public static String getIpPort(int port){
        String ip=getIp();
        return getIpPort(ip,port);
    }


    public static String getIpPort(String ip,int port){
        if (ip == null) {
            return null;
        }
        return ip.concat(":").concat(String.valueOf(port));
    }

    public static Object[] parseIpPort(String address){
        String[] array=address.split(":");
        String host=array[0];
        int port=Integer.parseInt(array[1]);

        return new Object[]{host,port};
    }

    public static void main(String[] args) {
        System.out.println(getLocalAddress());
    }
}
