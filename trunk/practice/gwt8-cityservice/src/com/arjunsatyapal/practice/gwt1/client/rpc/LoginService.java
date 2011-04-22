package com.arjunsatyapal.practice.gwt1.client.rpc;

import com.arjunsatyapal.practice.gwt1.client.dtos.SessionKeyServiceReturnDto;
import com.arjunsatyapal.practice.gwt1.client.exceptions.FailedLoginException;
import com.arjunsatyapal.practice.gwt1.client.exceptions.PasswordNotChangedException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("login")
public interface LoginService
    extends RemoteService {
  void changePassword(
      String name,
      String encryptedNewPassword,
      String nonce,
      String parametersHash)
      throws PasswordNotChangedException;

  SessionKeyServiceReturnDto getSessionKey(
      String name,
      String nonce,
      String passHash)
      throws FailedLoginException;

  String getSomething(String name, String pass);
}