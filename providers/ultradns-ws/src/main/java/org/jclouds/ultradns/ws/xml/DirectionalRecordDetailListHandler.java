/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
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
package org.jclouds.ultradns.ws.xml;

import static org.jclouds.util.SaxUtils.equalsOrSuffix;

import org.jclouds.http.functions.ParseSax;
import org.jclouds.ultradns.ws.domain.DirectionalRecordDetail;
import org.xml.sax.Attributes;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.google.inject.Inject;

/**
 * 
 * @author Adrian Cole
 */
public class DirectionalRecordDetailListHandler extends
      ParseSax.HandlerForGeneratedRequestWithResult<FluentIterable<DirectionalRecordDetail>> {

   private final DirectionalRecordDetailHandler directionalRecordHandler;

   private final Builder<DirectionalRecordDetail> drs = ImmutableSet.<DirectionalRecordDetail> builder();

   @Inject
   public DirectionalRecordDetailListHandler(DirectionalRecordDetailHandler directionalRecordHandler) {
      this.directionalRecordHandler = directionalRecordHandler;
   }

   @Override
   public FluentIterable<DirectionalRecordDetail> getResult() {
      return FluentIterable.from(drs.build());
   }

   @Override
   public void startElement(String url, String name, String qName, Attributes attributes) {
      directionalRecordHandler.startElement(url, name, qName, attributes);
   }

   @Override
   public void endElement(String uri, String name, String qName) {
      if (equalsOrSuffix(qName, "DirectionalDNSRecordDetail")) {
         drs.add(directionalRecordHandler.getResult());
      }
   }
}
