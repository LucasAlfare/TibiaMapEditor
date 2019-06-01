package parsers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;

//TODO: arrumar a questao desse seeker ai...
public class ParserBase {

    private byte[] bytesDoArquivo;
    public int indexSeeker = 0;
    public int lastIndexSeeker = 0;

    public ParserBase(String filePath) throws IOException {
        bytesDoArquivo = Files.readAllBytes(Paths.get(filePath));
    }

    /**
     * Esse metodo auxiliar retorna um novo array contendo apenas
     * um trecho de um outro array. Isso e literalmente o tal "buffer".
     *
     * @param fonte
     * @param inicio
     * @param tamanho
     * @return um buffer...
     */
    public byte[] bufferDe(byte[] fonte, int inicio, int tamanho) {
        indexSeeker += tamanho;
        byte[] r = new byte[tamanho];
        System.arraycopy(fonte, inicio, r, 0, r.length);
        return r;
    }

    /**
     * Usado no lugar de "getUint8()", do JS
     *
     * @return
     */
    public byte lerByte() {
        return lerByte(indexSeeker);
    }

    /**
     * Usado no lugar de "getUint8()", do JS
     *
     * @param inicio
     * @return
     */
    public byte lerByte(int inicio) {
        return ByteBuffer
                .wrap(bufferDe(bytesDoArquivo, inicio, 1))
                .order(ByteOrder.LITTLE_ENDIAN)
                .get();
    }

    /**
     * retorna 1 (um) INT unsigned correspondente aos bytesDoArquivo repassados.
     * <p>
     * usado no lugar de "getUint16()"
     *
     * @return
     */
    public int lerShort() {
        return lerShort(indexSeeker);
    }

    /**
     * retorna 1 (um) INT unsigned correspondente aos bytesDoArquivo repassados.
     * <p>
     * usado no lugar de "getUint16()"
     *
     * @param
     * @return
     */
    public int lerShort(int inicio) {
        return (int) (char)
                (ByteBuffer
                        .wrap(bufferDe(bytesDoArquivo, inicio, 2))
                        .order(ByteOrder.LITTLE_ENDIAN)
                        .getShort());
    }

    /**
     * retorna 1 (um) LONG correspondente aos bytesDoArquivo repassados.
     * <p>
     * usado no lugar de "getUint32()"
     *
     * @return
     */
    public long lerInt() {
        return lerInt(indexSeeker);
    }

    /**
     * retorna 1 (um) LONG correspondente aos bytesDoArquivo repassados.
     * <p>
     * usado no lugar de "getUint32()"
     *
     * @param
     * @return
     */
    public long lerInt(int inicio) {
        return ByteBuffer
                .wrap(bufferDe(bytesDoArquivo, inicio, 4))
                .order(ByteOrder.LITTLE_ENDIAN)
                .getInt() & 0xFFFFFFFFL;
    }

    public void setIndexSeeker(int indexSeeker) {
        this.indexSeeker = indexSeeker;
    }

    public void resetSeeker() {
        this.lastIndexSeeker = this.indexSeeker;
        this.indexSeeker = 0;
    }

    public void restoreSeeker() {
        this.indexSeeker = this.lastIndexSeeker;
    }
}
