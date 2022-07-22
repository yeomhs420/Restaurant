package com.example.rest.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data   // getter setter ToString 등등의 기능
public class MemoryDbEntity {
    protected Integer index;
}
