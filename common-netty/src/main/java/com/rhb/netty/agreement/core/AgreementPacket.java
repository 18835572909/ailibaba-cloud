package com.rhb.netty.agreement.core;

import com.rhb.netty.agreement.protocol.friend.AddFriendRequest;
import com.rhb.netty.agreement.protocol.friend.AddFriendResponse;
import com.rhb.netty.agreement.protocol.friend.SearchFriendRequest;
import com.rhb.netty.agreement.protocol.friend.SearchFriendResponse;
import com.rhb.netty.agreement.protocol.login.LoginRequest;
import com.rhb.netty.agreement.protocol.login.LoginResponse;
import com.rhb.netty.agreement.protocol.login.ReconnectRequest;
import com.rhb.netty.agreement.protocol.msg.MsgGroupRequest;
import com.rhb.netty.agreement.protocol.msg.MsgGroupResponse;
import com.rhb.netty.agreement.protocol.msg.MsgRequest;
import com.rhb.netty.agreement.protocol.msg.MsgResponse;
import com.rhb.netty.agreement.protocol.talk.DelTalkRequest;
import com.rhb.netty.agreement.protocol.talk.TalkNoticeRequest;
import com.rhb.netty.agreement.protocol.talk.TalkNoticeResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/27 11:28
 */
public abstract class AgreementPacket {

  private static final Map<Byte,Class<? extends AgreementPacket>> commandContext = new ConcurrentHashMap<>(14);

  static {
    commandContext.put(Command.LoginRequest, LoginRequest.class);
    commandContext.put(Command.LoginResponse, LoginResponse .class);
    commandContext.put(Command.MsgRequest, MsgRequest.class);
    commandContext.put(Command.MsgResponse, MsgResponse.class);
    commandContext.put(Command.TalkNoticeRequest, TalkNoticeRequest.class);
    commandContext.put(Command.TalkNoticeResponse, TalkNoticeResponse.class);
    commandContext.put(Command.SearchFriendRequest, SearchFriendRequest.class);
    commandContext.put(Command.SearchFriendResponse, SearchFriendResponse.class);
    commandContext.put(Command.AddFriendRequest, AddFriendRequest.class);
    commandContext.put(Command.AddFriendResponse, AddFriendResponse.class);
    commandContext.put(Command.DelTalkRequest, DelTalkRequest.class);
    commandContext.put(Command.MsgGroupRequest, MsgGroupRequest.class);
    commandContext.put(Command.MsgGroupResponse, MsgGroupResponse .class);
    commandContext.put(Command.ReconnectRequest, ReconnectRequest .class);
  }

  public abstract Byte getCommand();

  public static Class<? extends AgreementPacket> get(Byte command){
    return commandContext.get(command);
  }

}
