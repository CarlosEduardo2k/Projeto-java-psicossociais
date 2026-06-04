package Painel.Questionario;
import java.util.ArrayList;
import java.util.List;

public class BancoPerguntas {
    public List<Pergunta> carregarPerguntas(){
        List<Pergunta> perguntas = new ArrayList<>();
        perguntas.add(
                new Pergunta(
                        1,"Existe boa comunicação entre equipe e liderança.",Categoria.RT)
        );
        perguntas.add(
                new Pergunta(
                        2,"As demandas e prazos do meu trabalho geram pressão excessiva.",Categoria.OT)
        );
        perguntas.add(
                new Pergunta(
                        3,"Sinto satisfação e orgulho pelo trabalho que realizo.",Categoria.RP)
        );
        perguntas.add(
                new Pergunta(
                        4,"Os equipamentos e materiais disponíveis atendem às necessidades das tarefas.",Categoria.CT)
        );
        perguntas.add(
                new Pergunta(
                        5,"Meu trabalho me causa estresse, ansiedade ou desgaste emocional frequente.",Categoria.DE)
        );
        perguntas.add(
                new Pergunta(
                        6,"Tenho liberdade para organizar minhas atividades da maneira que considero adequada.",Categoria.LA)
        );
        perguntas.add(
                new Pergunta(
                        7,"As normas e processos do meu trabalho dificultam a execução das tarefas.",Categoria.OT)
        );
        perguntas.add(
                new Pergunta(
                        8,"Sinto-me desvalorizado ou injustiçado no ambiente de trabalho.",Categoria.DP)
        );
        perguntas.add(
                new Pergunta(
                        9,"O ambiente de trabalho possui cooperação e companheirismo entre os colaboradores.",Categoria.RT)
        );
        perguntas.add(
                new Pergunta(
                        10,"O ritmo de trabalho é intenso e desgastante.",Categoria.OT)
        );
        perguntas.add(
                new Pergunta(
                        11,"A empresa oferece suporte para desenvolvimento e capacitação profissional.",Categoria.CT)
        );
        perguntas.add(
                new Pergunta(
                        12,"Sinto reconhecimento e valorização pelas atividades que desempenho.",Categoria.RP)
        );
        perguntas.add(
                new Pergunta(
                        13,"Preciso improvisar frequentemente para conseguir realizar minhas atividades.",Categoria.OT)
        );
        perguntas.add(
                new Pergunta(
                        14,"Tenho receio de não conseguir atender às exigências do trabalho.",Categoria.DP)
        );
        perguntas.add(
                new Pergunta(
                        15,"As condições de segurança são adequadas para o exercício das atividades.",Categoria.CT
                )
        );
        perguntas.add(
                new Pergunta(
                        16,"Sinto sobrecarga física ou mental no meu trabalho.",Categoria.DE
                )
        );
        perguntas.add(
                new Pergunta(
                        17,"Meu trabalho é compatível com meus objetivos e necessidades profissionais.",Categoria.RP
                )
        );
        perguntas.add(
                new Pergunta(
                        18,"Percebo tratamento desigual ou favorecimento entre colaboradores.",Categoria.RT
                )
        );
        perguntas.add(
                new Pergunta(
                        19,"Tenho liberdade para expressar opiniões e dificuldades relacionadas ao trabalho.",Categoria.LA
                )
        );
        perguntas.add(
                new Pergunta(
                        20,"A quantidade de funcionários é suficiente para a demanda de trabalho.",Categoria.CT
                )
        );
        perguntas.add(
                new Pergunta(
                        21,"Existe controle excessivo sobre a execução do meu trabalho.",Categoria.OT
                )
        );
        perguntas.add(
                new Pergunta(
                        22,"O relacionamento com clientes, usuários ou público é desgastante emocionalmente.",Categoria.RT
                )
        );
        perguntas.add(
                new Pergunta(
                        23,"As tarefas exigem alto nível de atenção e concentração durante grande parte do tempo.",Categoria.OT
                )
        );
        perguntas.add(
                new Pergunta(
                        24,"A empresa oferece estrutura adequada para a realização do trabalho.",Categoria.CT
                )
        );
        perguntas.add(
                new Pergunta(
                        25,"Sinto que posso participar ou opinar sobre decisões relacionadas ao meu trabalho.",Categoria.RT
                )
        );

        return perguntas;
    }
}
