/*

 * Copyright (C) 2020-2022 Huawei Technologies Co., Ltd. All rights reserved.

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huaweicloud.sample;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthControllerIT {

  final String gatewayUrl = "http://127.0.0.1:10088";

  final String orderServiceUrl = "http://127.0.0.1:9098";

  final String accountServiceUrl = "http://127.0.0.1:9092";

  final RestTemplate template = new RestTemplate();

  @Test
  public void testCheckTokenSucesssFromGateway() {
    String result = template.getForObject(gatewayUrl + "/order/checkToken", String.class);
    assertThat(result).isEqualTo("success");
  }

  @Test
  public void testCheckTokenSucesssFromConsumer() {
    String result = template.getForObject(orderServiceUrl + "/checkToken", String.class);
    assertThat(result).isEqualTo("success");
  }

  @Test
  public void testCheckTokenFailFromOther() {
    boolean exception = false;
    try {
      template.getForObject(accountServiceUrl + "/checkToken", String.class);
    } catch (Exception e) {
      exception = true;
    }
    assertThat(exception).isEqualTo(true);
  }

}
