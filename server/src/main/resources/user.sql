CREATE TABLE tray_user
(
  uid              varchar(64) PRIMARY KEY,             -- 用户ID
  username         varchar(128),                        -- 用户名称
  password         varchar (128),                       --用户密码
  createMillisTime datetime,                            --创建时间
  ip               varchar (128)                        --ip地址
)ENGINE=InnoDB DEFAULT CHARSET=utf8;