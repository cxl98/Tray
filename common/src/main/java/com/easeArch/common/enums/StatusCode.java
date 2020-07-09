package com.easeArch.common.enums;

public enum  StatusCode {
    /** 成功 */
    SUCCESS("1", "成功"),

    /** 参数校验失败**/
    VALIDATION_FAIL("2", "参数校验失败"),
    /** 失败 */
    FAIL("3", "失败"),

    /** 重复登录 */
    REPEAT_LOGIN("4", "账号重复登录，请退出一个账号！"),

    /** 账号不在线 */
    OFF_LINE("5", "你选择的账号不在线，请重新选择！"),

    /** 登录信息不匹配 */
    ACCOUNT_NOT_MATCH("6", "登录信息不匹配！");

    private final String code;

    private final String message;

    StatusCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 得到枚举值码。
     * @return 枚举值码。
     */
    public String getCode() {
        return code;
    }

    /**
     * 得到枚举描述。
     * @return 枚举描述。
     */
    public String getMessage() {
        return message;
    }

    public static String macth(String code){
        for (StatusCode item:
             StatusCode.values()) {
            if (code.equals(item.getCode())) {
                return item.getMessage();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String macth = macth("1");
        System.out.println(macth);
    }
}
