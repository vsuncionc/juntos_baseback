package com.base.base.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BaseUtility {
    public static String  toStr(Object valor) {
        if(valor==null){
            return "";
        }

        return valor.toString();

    }
}
