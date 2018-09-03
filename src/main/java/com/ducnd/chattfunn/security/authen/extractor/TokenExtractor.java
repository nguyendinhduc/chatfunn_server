package com.ducnd.chattfunn.security.authen.extractor;

public interface TokenExtractor {
    String extract(String payload);
}
