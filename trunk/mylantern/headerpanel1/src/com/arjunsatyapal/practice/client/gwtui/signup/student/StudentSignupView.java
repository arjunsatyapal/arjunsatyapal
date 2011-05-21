package com.arjunsatyapal.practice.client.gwtui.signup.student;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.View;

public class StudentSignupView extends View {

  private static StudentSignupViewUiBinder uiBinder = GWT
      .create(StudentSignupViewUiBinder.class);

  interface StudentSignupViewUiBinder extends UiBinder<Widget, StudentSignupView> {
  }

  public StudentSignupView() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}
