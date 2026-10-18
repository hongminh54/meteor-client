import com.hongminh54.marisaoptimize.systems.accounts.AccountCache;
import com.hongminh54.marisaoptimize.systems.accounts.types.CrackedAccount;
import com.hongminh54.marisaoptimize.utils.misc.NbtException;
import net.minecraft.nbt.CompoundTag;

import java.util.Set;

/** Never logs in or fetches a head; an empty UUID takes the no-network path. */
public class AccountSerializationCheck {
    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    public static void main(String[] args) {
        CrackedAccount account = new CrackedAccount("MigrationTest");
        account.getCache().username = "CachedName";
        CompoundTag tag = account.toTag();
        check(tag.keySet().equals(Set.of("type", "name", "cache")), "Account schema changed");
        check(tag.getStringOr("type", "").equals("Cracked"), "Account type changed");
        CrackedAccount restored = new CrackedAccount(null).fromTag(tag);
        check(restored.getUsername().equals("CachedName"), "Cached username round trip failed");
        check(restored.toTag().equals(tag), "Account NBT round trip failed");
        check(new AccountCache().toTag().keySet().equals(Set.of("username", "uuid")), "Cache schema changed");
        try {
            restored.fromTag(new CompoundTag());
            throw new AssertionError("Missing account fields accepted");
        } catch (NbtException expected) {
            // Keep existing invalid-config behavior.
        }
        System.out.println("Account checks passed: schema, cached username, NBT round trip and required fields; no login/network performed.");
    }
}
