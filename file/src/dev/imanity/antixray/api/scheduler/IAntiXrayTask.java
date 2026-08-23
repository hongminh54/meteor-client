// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.scheduler;

import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;
import java.util.concurrent.Callable;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H&¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H&¢\u0006\u0004\b\b\u0010\u0007J\u0017\u0010\t\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H&¢\u0006\u0004\b\t\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\nH&¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\r\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\nH&¢\u0006\u0004\b\r\u0010\fJ\u000f\u0010\u000e\u001a\u00020\u0005H&¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0005H&¢\u0006\u0004\b\u0010\u0010\u000f" }, d2 = { "Ldev/imanity/antixray/api/scheduler/IAntiXrayTask;", "Ljava/util/concurrent/Callable;", "", "Ljava/lang/Runnable;", "p0", "", "addAsyncTask", "(Ljava/lang/Runnable;)V", "addMainTask", "addReinitializeTask", "", "delay", "(J)V", "reinitialize", "start", "()V", "stop" })
public interface IAntiXrayTask extends Callable<Object>
{
    void delay(final long p0);
    
    void start();
    
    void reinitialize(final long p0);
    
    void stop();
    
    void addMainTask(@NotNull final Runnable p0);
    
    void addAsyncTask(@NotNull final Runnable p0);
    
    void addReinitializeTask(@NotNull final Runnable p0);
}
