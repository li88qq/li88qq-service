-- username KEY_USERNAME
alter table `User`
    add unique KEY_USERNAME(`username`);

-- AppConfig appCode
alter table `AppConfig`
    add unique KEY_CODE(appCode);

-- uid KEY_UID
alter table `ActionLog`
    add key KEY_UID(uid);
alter table `LoginLog`
    add key KEY_UID(uid);
