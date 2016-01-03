package org.panda_lang.panda.lang.net;

import org.panda_lang.panda.core.Particle;
import org.panda_lang.panda.core.VialCenter;
import org.panda_lang.panda.core.syntax.*;
import org.panda_lang.panda.lang.PNull;
import org.panda_lang.panda.lang.PNumber;
import org.panda_lang.panda.lang.PObject;

import java.io.IOException;
import java.net.ServerSocket;

public class PServerSocket extends PObject {

    private static final Vial vial;

    static {
        vial = VialCenter.initializeVial("ServerSocket");
        vial.group("panda.network");
        vial.constructor(new Constructor() {
            @Override
            public Essence run(Particle particle) {
                PNumber port = particle.get(0, PNumber.class);
                try {
                    return new PServerSocket(port.getNumber().intValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return new PNull();
            }
        });
        vial.method(new Method("accept", new Executable() {
            @Override
            public Essence run(Particle particle) {
                PServerSocket serverSocket = particle.getInstance(PServerSocket.class);
                try {
                    return new PSocket(serverSocket.getServerSocket().accept());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return new PNull();
            }
        }));
    }

    private final ServerSocket serverSocket;

    public PServerSocket(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);

    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    @Override
    public String toString() {
        return serverSocket.toString();
    }

}