package com.example.libraryjpa.transfer;

import java.util.List;

public record AppInfo(String name, int loanDays, String supportEmail, List<String> profiles) {
}
