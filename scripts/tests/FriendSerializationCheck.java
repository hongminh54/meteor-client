import com.hongminh54.marisaoptimize.systems.friends.Friend;
import com.hongminh54.marisaoptimize.systems.friends.Friends;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import java.util.UUID;

public class FriendSerializationCheck {
    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    public static void main(String[] args) {
        UUID id = UUID.fromString("01234567-89ab-cdef-0123-456789abcdef");
        var online = new Friend("Zebra", id);
        check(online.toTag().getStringOr("id", "").equals("0123456789abcdef0123456789abcdef"), "UUID schema changed");
        check(!new Friend("Alex").toTag().contains("id"), "Offline friend gained UUID");
        var entries = new ListTag();
        entries.add(online.toTag());
        entries.add(new Friend("Alex").toTag());
        entries.add(new Friend("zEBRA").toTag());
        entries.add(new CompoundTag());
        var tag = new CompoundTag();
        tag.put("friends", entries);
        var friends = new Friends().fromTag(tag);
        check(friends.count() == 2, "Duplicate or missing-name handling changed");
        check(friends.iterator().next().name.equals("Alex"), "Case-insensitive sort changed");
        check(friends.get("ZEBRA") != null, "Case-insensitive lookup changed");
        var restored = new Friends().fromTag(friends.toTag());
        check(restored.count() == 2 && restored.get("Zebra").toTag().equals(online.toTag()), "Friend round trip changed");
        check(!friends.add(new Friend("Alex")) && !friends.add(new Friend("bad name")), "Add validation changed");
        check(friends.remove(friends.get("Alex")) && friends.count() == 1, "Removal changed");
        System.out.println("Friend checks passed: UUID schema, offline IDs, deduplication, sorting, lookup and NBT round trip; disk/head refresh/executor stubbed, no HTTP performed.");
    }
}
