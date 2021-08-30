package com.trade.project.member.application;

import java.util.Collections;
import java.util.List;

public enum MemberRoleContext implements ExportRole{
    USER(() -> Collections.singletonList("ROLE_USER")),
    ADMIN(() -> Collections.singletonList("ROLE_ADMIN")),
    DROP (() -> Collections.singletonList("ROLE_DROP"));

    private final ExportRole e;

    MemberRoleContext(ExportRole e) {
        this.e = e;
    }

    @Override
    public List<String> exportRole() {
        return e.exportRole();
    }
}
