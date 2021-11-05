package com.leaflogix.enums

object BackendParams extends Enumeration {

  val EMPLOYEE_LOGIN_NAME = "Log In"
  val EMPLOYEE_LOGIN_PATH = "/api/posv3/user/EmployeeLogin"
  val EMPLOYEE_LOGIN_REQUEST = "data/templates/backend/user/employee_login_request.json"
  val EMPLOYEE_LOGIN_RESPONSE = "data/templates/backend/user/employee_login_response.json"

}
