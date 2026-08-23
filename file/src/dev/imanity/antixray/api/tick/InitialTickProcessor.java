// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.api.tick;

import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 9, 0 }, k = 1, xi = 48, d1 = { "\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\bH&¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH&¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0004H&¢\u0006\u0004\b\u000f\u0010\u0010" }, d2 = { "Ldev/imanity/antixray/api/tick/InitialTickProcessor;", "", "Ldev/imanity/antixray/api/tick/DelayInitialTick;", "p0", "", "addDelay", "(Ldev/imanity/antixray/api/tick/DelayInitialTick;)V", "", "Ljava/lang/Runnable;", "p1", "addTask", "(ILjava/lang/Runnable;)V", "", "isWaitingForInitTick", "()Z", "process", "()V" })
public interface InitialTickProcessor
{
    void addTask(final int p0, @NotNull final Runnable p1);
    
    void addDelay(@NotNull final DelayInitialTick p0);
    
    void process();
    
    boolean isWaitingForInitTick();
}
