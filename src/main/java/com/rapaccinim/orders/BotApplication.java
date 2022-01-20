package com.rapaccinim.orders;

import com.symphony.bdk.core.SymphonyBdk;
import com.symphony.bdk.gen.api.model.Stream;
import com.symphony.bdk.gen.api.model.UserV2;
import com.symphony.bdk.gen.api.model.V3RoomAttributes;
import com.symphony.bdk.gen.api.model.V3RoomDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import static com.symphony.bdk.core.config.BdkConfigLoader.loadFromClasspath;
import static com.symphony.bdk.core.activity.command.SlashCommand.slash;
import static com.symphony.bdk.core.config.BdkConfigLoader.loadFromFile;
import static java.util.Collections.singletonMap;

/**
 * Simple Bot Application.
 */
public class BotApplication {

  private static final Logger LOGGER = LoggerFactory.getLogger(BotApplication.class);

  private static void sendMessageToMyself(SymphonyBdk bdk, Long userId){

    // create a stream between the bot and my user
    Stream stream = bdk.streams().create(userId);
    // send a message
    bdk.messages().send(stream.getId(), "Hello, this is your userId: " + userId.toString());

  }

  private static void createRoomWithMyself(SymphonyBdk bdk, Long userId){

    V3RoomAttributes roomAttributes = new V3RoomAttributes()
            .name("Test Room")
            .description("Test Room description");
    // let's create a new room using the roomAttributes
    V3RoomDetail v3RoomDetail = bdk.streams().create(roomAttributes);

    // I need the stream id
    String roomId = v3RoomDetail.getRoomSystemInfo().getId();

    // I add myself to the room
    bdk.streams().addMemberToRoom(userId, roomId);

    // the bot sends the message to the room
    bdk.messages().send(roomId, "Welcome userId " + userId + " to the room !");

  }

  public static void main(String[] args) throws Exception {

    // Initialize BDK entry point
    // final SymphonyBdk bdk = new SymphonyBdk(loadFromClasspath("/config.yaml"));
    final SymphonyBdk bdk = new SymphonyBdk(loadFromFile("config.yaml"));

    // let's query for my user account (in this case I am using my email)
    List<UserV2> users = bdk.users().listUsersByUsernames(List.of("marco@t360degrees.com"));
    long myUserId = users.get(0).getId();

    // test how to send a single message (from the bot to myself)
    sendMessageToMyself(bdk, myUserId);

    // test how to create a room with myself and the bot
    createRoomWithMyself(bdk, myUserId);
  }
}
