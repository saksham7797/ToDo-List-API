package com.saksham.ToDoListApi.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UpdatePasswordRequest {
    private String oldPassword;
    private String newPassword;
}
