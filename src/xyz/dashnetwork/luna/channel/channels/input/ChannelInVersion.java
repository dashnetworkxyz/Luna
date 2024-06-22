package xyz.dashnetwork.luna.channel.channels.input;

import com.google.common.io.ByteArrayDataInput;
import xyz.dashnetwork.luna.channel.Channel;

import java.util.UUID;

public class ChannelInVersion extends Channel {

    @Override
    protected void receive(ByteArrayDataInput input) {
        UUID uuid = UUID.fromString(input.readUTF());
        String version = input.readUTF();
    }

}
