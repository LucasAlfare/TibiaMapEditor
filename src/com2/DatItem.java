package com2;

public class DatItem {
    //É um chão?
    public groundtile GroundTile = new groundtile();

    //Top order 1
    public boolean TopOrder1 = false; //sempre no topo prioridade alta, novo ???

    //Top order 2
    public boolean TopOrder2 = false; //sempre no topo baixa prioridade ???

    //Top order 3
    public boolean TopOrder3 = false; //pode andar - portas etc ???

    //Quando dá para colocar coisas dentro disso
    public boolean IsContainer = false;

    //amontoavel, empilhavel, varios ;D
    public boolean IsStackable = false;

    //Corpo
    public boolean IsCorpse = false;

    //Usavel
    public boolean IsUseable = false;

    //Rune
    public boolean IsRune = false;

    //É escrevivel
    public writeable Writeable = new writeable();

    //Apenas para leitura
    public onlyreadable OnlyReadable = new onlyreadable();

    //Fluidos
    public boolean IsFluidContainer = false;

    //Splash
    public boolean IsSplash = false;

    //Bloqueia a passagem
    public boolean IsBlocking = false;

    //Impossível de se mover
    public boolean IsImmobile = false;

    //Impede que uma coisa que foi atirada chegue em outro jogador
    public boolean IsMissileBlocking = false;

    //Impede que uma magia de area atravesse
    public boolean IsPathBlocking = false;

    //??????????
    public ExtraInfo ExtraInfo = new ExtraInfo();

    //Pode ser pego
    public boolean IsPickUpAble = false;

    //Decoração fora da parede
    public boolean IsHangableOff = false;

    //Decoração na parede, na horizontal
    public boolean IsHangableHorizontal = false;

    //Decoração na parede, na vertical
    public boolean IsHangableVertical = false;

    //Rodavel
    public boolean IsRotatable = false;

    //Tem luz?
    public light Light = new light();

    //É capaz de alterar sua posição em Z? (escadas e buracos)
    public boolean IsChangeFloor = false;

    //Deslocamento da sprite em X e Y em relação ao local onde ela é colocada (montanhas por exemplo)
    public offset Offset = new offset();

    //Deslocamento considerando a altura
    public raised Raised = new raised();

    //??
    public boolean IsLayer = false;

    //Animação ociosa??
    public boolean IsIdleAnimation = false;

    //esta ativo no minimapa?
    public minimap MiniMap = new minimap();

    public hasHelp HasHelp = new hasHelp();

    public boolean isGround = false; //Fecha o Ground será??

    //VARIAVEIS REFERENTE AS SPRITES
    public byte Width; //largura em sprites

    public byte Height; //altura em sprites

    //if (width > 1 || height > 1) { apare um Unknow }
    public byte BlendFrame; //quando você tem um outfit com opção de mudar de cor

    public byte
            XRepeat; //repetição do item em direção X, por exemplo, se você põe um item 100 (VOID) um do lado do outro, os dois não são iguais, mas tem um padrão de repetição

    public byte
            YRepeat; //Yrepet é a mesma coisa que o X, e ainda, o padrão é dado por valores como 1, 2, 3, 4 ext, se o padrão for 4, então a repetição será 1234123412334, se for 2 o padrão será 1212121212

    public byte ZRepeat;
    public byte Animation;

    public int SpritesNumber; //quantidade de sprites
    public int[] SpritesId;

    public void CountSprites() {
        if (Width > 0 && Height > 0 && BlendFrame > 0 && XRepeat > 0 && ZRepeat > 0 && YRepeat > 0 &&
                Animation > 0) {
            SpritesNumber = Width * Height * BlendFrame * XRepeat * YRepeat * ZRepeat * Animation;
            SpritesId = new int[SpritesNumber];
        }
    }

    //Eu sou um??
    //0 = item
    //1 = criaturas
    //2 - efeitos
    //3 - misseis
    public int IAmA;

    //Definir o que eu sou (Itens lidos, necessarios para eu ser uma criatura, mesma coisa para eu ser um efeito, o mesmo para um missil)
    public void setWhatIAm(int ItensRead, int ItemsTotal, int CreaturesTotal,
                           int EffectsTotal) //Missiles não é necessario, ele é feio, ai a gente faz bullying ;D
    {
        CreaturesTotal += ItemsTotal;
        EffectsTotal += CreaturesTotal;
        if (ItensRead < ItemsTotal
        ) //Se ainda não tem itens lidos o suficiente para ser uma criatura, você é um item
        {
            IAmA = 0;
        } else if (ItensRead < CreaturesTotal
        ) //Se você ainda não tem itens lidos o suficiente para ser um efeito, você é uma criatura
        {
            IAmA = 1;
        } else if (ItensRead < EffectsTotal
        ) //Se você ainda não tem itens lidos o suficiente para ser um missil, você é um efeito
        {
            IAmA = 2;
        } else // quando você já tem itens lidos suficiente para ser um missil ;D
        {
            IAmA = 3;
        }
    }

    //avisando que um byte desconhecido foi encontrado
    int unknows = 0;
    int[] myunks;

    public void Unknow(int TheUnknowed) {
        unknows++;
        if (myunks != null) {
            int[] backup = new int[myunks.length];
            //fazendo backpack porque a estrutura do array MYUNKS vai ser alterada
            System.arraycopy(myunks, 0, backup, 0, myunks.length);

            myunks = new int[unknows];
            //recebendo de volta os valores do backup
            System.arraycopy(backup, 0, myunks, 0, backup.length);
        } else {
            myunks = new int[unknows];
        }

        myunks[unknows - 1] = TheUnknowed; //recebendo o novo valor
    }

    //Guardando exatamente o byte de cada item para quando for salvar, salvar exatamente da forma como estava
    int bytesindex = 0;
    public int[][] mybytes;

    public void AddByte(int ByteAdded, String ByteType) {
        bytesindex++;
        if (mybytes != null) {
            int[][] backup = new int[mybytes.length][2];
            //fazendo backpack porque a estrutura da matriz MYBYTES vai ser alterada
            for (int i = 0; i < mybytes.length; i++) {
                backup[i][0] = mybytes[i][0];
                backup[i][1] = mybytes[i][1];
            }

            mybytes = new int[bytesindex][2];
            //recebendo de volta os valores do backup
            for (int i = 0; i < backup.length; i++) {
                mybytes[i][0] = backup[i][0];
                mybytes[i][1] = backup[i][1];
            }
        } else {
            mybytes = new int[bytesindex][2];
        }

        mybytes[bytesindex - 1][0] = ByteAdded; //recebendo o novo valor
        //adicionando o novo parâmetro que vai indicar o estilo do baguio no richtext
        switch (ByteType) {
            case "att":
                mybytes[bytesindex - 1][1] = 0;
                break;
            case "param":
                mybytes[bytesindex - 1][1] = 1;
                break;
            case "char":
                mybytes[bytesindex - 1][1] = 2;
                break;
            case "end":
                mybytes[bytesindex - 1][1] = 3;
                break;
            case "sprparam":
                mybytes[bytesindex - 1][1] = 4;
                break;
            case "sprid1":
                mybytes[bytesindex - 1][1] = 5;
                break;
            case "sprid2":
                mybytes[bytesindex - 1][1] = 6;
                break;
        }
    }

    //É um chão?
    class groundtile {
        //É um chão?
        public boolean Is = false;
        public int Speed; //Velocidade do player quando andar nesse chão - 0 significa que o player não pode andar
    }

    //É escrevivel
    class writeable {
        public boolean Is = false;
        public int Maxlength;
    }

    //Apenas para leitura
    class onlyreadable {
        public boolean Is = false;
        public int Maxlength;
    }

    //luz
    class light {
        public boolean Is = false;
        public int Radius; //raio
        public int Color; //cor -- 0 significa que não há luz, coisa tosca né?
    }

    //deslocamento
    class offset {
        //[is offset] + [2 bytes X offset] + [2 bytes Y offset]. Offset in pixels to draw the sprite from the tile
        public boolean Is = false;
        public int X;
        public int Y;
    }

    //deslocamento pela altura
    class raised {
        //[is raised] + [2 bytes height]. similar to offset, but both X and Y are offset by height
        public boolean Is = false;
        public int Height;
    }

    class minimap {
        public boolean Is = false;
        public int Color;
    }

    //If you use the client help function will anything show up
    class hasHelp {
        public boolean Is = false; //Tem ajuda?
        public String Value; //Valor da ajuda (1 Byte depois do que identifica o que é)

        public void setHelpValue(int helpbyte) {
            if (helpbyte == 0x4E) {
                Value = "IsRopeSpot";
            } else if (helpbyte == 0x4F) {
                Value = "IsSwitch";
            } else if (helpbyte == 0x50) {
                Value = "IsDoor";
            } else if (helpbyte == 0x51) {
                Value = "IsDoorWithLock";
            } else if (helpbyte == 0x52) {
                Value = "IsStairs";
            } else if (helpbyte == 0x53) {
                Value = "IsMailBox";
            } else if (helpbyte == 0x54) {
                Value = "IsDepot";
            } else if (helpbyte == 0x55) {
                Value = "IsTrash";
            } else if (helpbyte == 0x56) {
                Value = "IsHole";
            } else if (helpbyte == 0x57) {
                Value = "IsSpecialDescription";
            } else if (helpbyte == 0x58) {
                Value = "IsReadOnly";
            } else {
                Value = "Unknow";
            }
        }
    }

    public class ExtraInfo {

        public String Text = "";
        public int Length;
    }
}