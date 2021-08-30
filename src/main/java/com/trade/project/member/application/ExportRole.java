package com.trade.project.member.application;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface ExportRole {

    List<String> exportRole();
}
