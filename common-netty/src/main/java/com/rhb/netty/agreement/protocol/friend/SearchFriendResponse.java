package com.rhb.netty.agreement.protocol.friend;

import com.rhb.netty.agreement.core.Command;
import com.rhb.netty.agreement.core.AgreementPacket;
import com.rhb.netty.agreement.protocol.friend.dto.UserDto;
import java.util.List;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 *
 * 搜索好友应答
 */
public class SearchFriendResponse extends AgreementPacket {

    private List<UserDto> list;

    public List<UserDto> getList() {
        return list;
    }

    public void setList(List<UserDto> list) {
        this.list = list;
    }

    @Override
    public Byte getCommand() {
        return Command.SearchFriendResponse;
    }
}
