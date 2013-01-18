/*
 * Copyright Myrrix Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.myrrix.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility methods related to {@link ExecutorService} and related classes.
 *
 * @author Sean Owen
 */
public final class ExecutorUtils {

  private static final Logger log = LoggerFactory.getLogger(ExecutorUtils.class);

  private ExecutorUtils() {
  }

  /**
   * Immediately shuts down its argument and waits a short time for it to terminate.
   */
  public static void shutdownNowAndAwait(ExecutorService executor) {
    if (!executor.isTerminated()) {
      if (!executor.isShutdown()) {
        log.info("Shutting down executor {}", executor);
        executor.shutdownNow();
      }
      try {
        log.info("Waiting briefly for termination of {}", executor);
        executor.awaitTermination(5L, TimeUnit.SECONDS);
      } catch (InterruptedException ie) {
        log.warn("Interrupted while shutting down executor");
      }
    }
  }

}
