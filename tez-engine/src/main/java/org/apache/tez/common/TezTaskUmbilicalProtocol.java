/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.tez.common;

import java.io.IOException;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;
import org.apache.hadoop.ipc.ProtocolInfo;
import org.apache.hadoop.ipc.VersionedProtocol;
import org.apache.tez.common.records.ProceedToCompletionResponse;
import org.apache.tez.dag.api.TezException;
import org.apache.tez.dag.records.TezTaskAttemptID;
import org.apache.tez.engine.api.impl.TezHeartbeatRequest;
import org.apache.tez.engine.api.impl.TezHeartbeatResponse;

/** Protocol that task child process uses to contact its parent process.  The
 * parent is a daemon which which polls the central master for a new map or
 * reduce task and runs it as a child process.  All communication between child
 * and parent is via this protocol. */
@InterfaceAudience.Private
@InterfaceStability.Stable
@ProtocolInfo(protocolName = "TezTaskUmbilicalProtocol", protocolVersion = 1)
public interface TezTaskUmbilicalProtocol extends VersionedProtocol {

  public static final long versionID = 19L;

  ContainerTask getTask(ContainerContext containerContext) throws IOException;

  boolean canCommit(TezTaskAttemptID taskid) throws IOException;

  ProceedToCompletionResponse
      proceedToCompletion(TezTaskAttemptID taskAttemptId) throws IOException;

  /// Copies from TezUmbilical until complete re-factor is done
  // TODONEWTEZ

  public TezHeartbeatResponse heartbeat(TezHeartbeatRequest request)
      throws IOException, TezException;

}
