package com.leaflogix.enums

object PosParams extends Enumeration {

  val SEARCH_GUEST_BY_STRING_NAME = "Search guest"
  val SEARCH_GUEST_BY_STRING_PATH = "/api/v2/guest/checkin_search_by_string"
  val SEARCH_GUEST_BY_STRING_REQUEST = "data/templates/pos/checkin_search_by_string_request.json"
  val SEARCH_GUEST_BY_STRING_RESPONSE = "data/templates/pos/checkin_search_by_string_response.json"

}
