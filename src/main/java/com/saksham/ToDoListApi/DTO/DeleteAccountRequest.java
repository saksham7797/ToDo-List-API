package com.saksham.ToDoListApi.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DeleteAccountRequest{
    private String password;
}