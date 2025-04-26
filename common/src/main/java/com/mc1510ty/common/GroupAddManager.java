package com.mc1510ty.common;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.InheritanceNode;
import net.luckperms.api.node.NodeEqualityPredicate;
import net.luckperms.api.util.Tristate;  // Tristate をインポート

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class GroupAddManager {

    public static void addPlayerGroup(UUID playerUUID) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        CompletableFuture<User> futureUser = luckPerms.getUserManager().loadUser(playerUUID);

        futureUser.thenAccept(user -> {
            if (user == null) return;

            InheritanceNode node = InheritanceNode.builder("player").build();

            // .contains() は Tristate を返す → asBoolean() で boolean チェック
            if (!user.data().contains(node, NodeEqualityPredicate.EXACT).asBoolean()) {
                user.data().add(node);
                luckPerms.getUserManager().saveUser(user);
            }
        }).exceptionally(err -> {
            err.printStackTrace();
            return null;
        });
    }
}
