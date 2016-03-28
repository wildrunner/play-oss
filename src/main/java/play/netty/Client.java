package play.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.net.URI;


/**
 * @author Inhee Kim
 */
public class Client {
    public static void main(String[] args) throws Exception {
        /*String host = args[0];
        int port = Integer.parseInt(args[1]);*/

        String host = "";
        String url = "";
        URI uri = new URI(url);
        int port = 10999;

        //URI uri = new URI(host);
        //QueryStringDecoder decoder = new QueryStringDecoder(uri);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                     .channel(NioSocketChannel.class)
                     .handler(new ChannelInitializer<SocketChannel>() {
                         @Override
                         public void initChannel(SocketChannel ch) throws Exception {
                             ChannelPipeline pipeline = ch.pipeline();
                             pipeline.addLast(new HttpClientCodec());
                             //pipeline.addLast(new HttpContentCompressor());
                             pipeline.addLast(new ClientHandler());
                         }
                     });

            Channel ch = bootstrap.connect(host, port).sync().channel();
            String rawPath = uri.toString();

            HttpRequest request = new DefaultFullHttpRequest(
                    HttpVersion.HTTP_1_1, HttpMethod.GET, uri.toString());
            request.headers().set(HttpHeaders.Names.HOST, host);
            request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.CLOSE);
            request.headers().set(HttpHeaders.Names.ACCEPT_ENCODING, HttpHeaders.Values.GZIP);

            ch.writeAndFlush(request);
            ch.closeFuture().sync();

        } finally {
            workerGroup.shutdownGracefully();
        }
    }

}

