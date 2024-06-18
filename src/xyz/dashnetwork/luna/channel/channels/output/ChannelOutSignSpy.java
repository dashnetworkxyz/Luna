package xyz.dashnetwork.luna.channel.channels.output;

import xyz.dashnetwork.luna.channel.Channel;

public final class ChannelOutSignSpy extends Channel {

    @Override
    protected void send(Object[] objects) {
        output.writeUTF(objects[0].toString()); // uuid
        output.writeInt((int) objects[1]); // x
        output.writeInt((int) objects[2]); // y
        output.writeInt((int) objects[3]); // z
        output.writeUTF(objects[4].toString()); // line 1
        output.writeUTF(objects[5].toString()); // line 2
        output.writeUTF(objects[6].toString()); // line 3
        output.writeUTF(objects[7].toString()); // line 4
    }

}
