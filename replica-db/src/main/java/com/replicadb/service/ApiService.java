package com.replicadb.service;

public interface ApiService {
    <T> T getSlave();
    void saveMaster();
}
