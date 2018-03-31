/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package org.apache.nutch.storage;

import java.util.HashMap;

public class Host {
    private HashMap<String,Object> outlinks;
    
    private HashMap<String,Object> inlinks;
        
    private HashMap<String,Object> metadata;

	public HashMap<String, Object> getOutlinks() {
		return outlinks;
	}

	public void setOutlinks(HashMap<String, Object> outlinks) {
		this.outlinks = outlinks;
	}

	public HashMap<String, Object> getInlinks() {
		return inlinks;
	}

	public void setInlinks(HashMap<String, Object> inlinks) {
		this.inlinks = inlinks;
	}

	public HashMap<String, Object> getMetadata() {
		return metadata;
	}

	public void setMetadata(HashMap<String, Object> metadata) {
		this.metadata = metadata;
	}

    
}
