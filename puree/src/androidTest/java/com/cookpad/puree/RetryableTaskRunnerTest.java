package com.cookpad.puree;

import android.test.AndroidTestCase;

import com.cookpad.puree.retryable.RetryableTaskRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class RetryableTaskRunnerTest extends AndroidTestCase {

    public void testEnsureToCallMeAfterSetTime() {
        final CountDownLatch latch = new CountDownLatch(1);

        RetryableTaskRunner retryableTaskRunner = new RetryableTaskRunner(new Runnable() {
            @Override
            public void run() {
                latch.countDown();
            }
        }, 10, 5);

        retryableTaskRunner.tryToStart();

        try {
            latch.await(30, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            fail();
        }
    }
}
