/*
 * Copyright (c) 2018 Logicalis S/A.
 * 
 * This file is part of eugenio-showcase
 * (see https://github.com/EugenioIoT).
 * 
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.logicalis.eugenio.eugenioshowcase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logicalis.eugenio.client.api.common.dtos.EugenioErrorResponse;
import com.logicalis.eugenio.client.exception.EugenioApiException;
import com.logicalis.eugenio.client.exception.EugenioUserUnloggedException;

@ControllerAdvice
public class EugenioControllerAdvice {

	@ExceptionHandler(EugenioApiException.class)
	@ResponseBody
	public ResponseEntity<EugenioErrorResponse> handlerApiException(EugenioApiException ex) {
		return ResponseEntity.status(ex.getResponse().getStatus()).body(ex.getErrorMessage());
	}

	@ExceptionHandler(EugenioUserUnloggedException.class)
	@ResponseBody
	public ResponseEntity<?> handlerUserUnloggedException(EugenioUserUnloggedException ex) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
	}

}
