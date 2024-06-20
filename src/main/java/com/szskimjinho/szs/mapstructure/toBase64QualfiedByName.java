package com.szskimjinho.szs.mapstructure;

import com.szskimjinho.szs.Utils.Base64Utils;
import org.mapstruct.Named;

public interface toBase64QualfiedByName {

    @Named("toBase64Encode")
    default String regNoToBase64Encode(String regNo){
        return Base64Utils.encode(regNo);
    }

    @Named("toBase64Decode")
    default String regNoToBase64Decode(String regNo){
        return Base64Utils.decode(regNo);
    }
}
