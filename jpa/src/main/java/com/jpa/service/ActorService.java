package com.jpa.service;

import com.jpa.enumerator.Gender;

public interface ActorService {
    void saveActor(String firstName);
    void saveActorRollbackFor(String firstName);
}
