# JogadorImPIvisivel - Estratégia Jokenpô baseada em Pi

## Sobre a Estratégia

Este jogador utiliza os dígitos do número Pi para decidir sua jogada, combinando tática e aleatoriedade para confundir o adversário:

- **Dígito de Pi < 3:** Contra-ataque simples — joga a jogada que vence a última jogada do oponente.
- **Dígito de Pi entre 3 e 6:** Ataca a jogada mais frequente do adversário até o momento.
- **Dígito de Pi > 6:** Escolhe uma jogada aleatória para bagunçar a cabeça do oponente.

Se perdeu a última partida, evita repetir a mesma jogada para não ser previsível.

## Como usar o código

1. Clone o repositório oficial do Jankenpon:
   ```bash
   https://github.com/guisso/JanKenPon
   https://github.com/guisso/JanKenPonManager
Copie a classe JogadorImPIvisivel.java para o pacote br.edu.ifnmg.aluno.pdss8.jogador1 do seu projeto.

Verifique se o seu projeto já tem as dependências do Jankenpon no pom.xml.

Compile e faça o build do projeto:

Execute o jogo usando o JankenponManager (configurado no pom.xml), que vai rodar sua estratégia automaticamente.

Adicione JogadorImPIvisivel na pasta players
Rode com JankenponManager
