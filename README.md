# Jogo de Blackjack

![Imagem de capa do projeto](/img/blackjack.png)

## Visão Geral

Este é um projeto de um jogo de blackjack jogável em Java, com testes unitários implementados usando o framework JUnit. O objetivo deste projeto é fornecer uma implementação funcional e uma suíte abrangente de testes para o jogo de blackjack.

## Funcionalidades Principais

### Exceções Implementadas

O projeto inclui um tratamento robusto de exceções para lidar com situações imprevistas durante o jogo. Este recurso garante uma experiência de jogo mais estável e confiável.

### Padrões de Projeto

O código incorpora alguns padrões de projeto fundamentais para promover uma estrutura modular e eficiente:

1. **Singleton Pattern:**
   - A classe `CSVDatabase` é implementada como um singleton, garantindo que apenas uma instância seja criada para salvar resultados e adicionar ao histórico.

2. **Observer Pattern:**
   - O uso de `IntegerProperty` e `StringProperty` do JavaFX segue o padrão Observer, permitindo atualizações dinâmicas na interface do usuário com base em alterações nos valores associados.

3. **Composite Pattern:**
   - A relação entre `Player` e `Card` sugere a aplicação do padrão Composite, onde `Player` representa a parte composta, contendo uma lista de `Card`, as partes individuais.

### Apresentação de Resultados de Partida

Os resultados das partidas são apresentados de forma clara e informativa. Após o término de uma partida, os jogadores podem visualizar quem venceu, a pontuação final e outros detalhes relevantes.

### Jogar Novamente ou Sair

Ao finalizar uma partida, os jogadores têm a opção de iniciar uma nova rodada imediatamente ou sair do jogo. Essa funcionalidade proporciona uma experiência contínua e amigável ao usuário.

## Tecnologias

<div style="display: flex; justify-content: center;">
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" alt="java" height="30" width="40">
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/vscode/vscode-original.svg" alt="vscode" height="30" width="40">
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original.svg" alt="git" height="30" width="40">
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/github/github-original.svg" alt="github" height="30" width="40">
</div>
<br/>

- Linguagem de Programação: Java
- Interface: JavaFX
- Versionamento e hospedagem: Git e Github
- Testes: Junit

## Como Jogar

1. Clone o repositório para o seu ambiente de desenvolvimento: `git clone https://github.com/seu-usuario/jogo-de-blackjack.git`
2. Navegue até o diretório do projeto: `cd BlackjackJavaGame`
3. Compile o projeto: `javac Main.java`
4. Execute o jogo: `java Main`

Siga as instruções na linha de comando para jogar o blackjack, inserindo suas decisões de apostas e jogadas.

## Testes Unitários

O projeto inclui uma suíte de testes unitários escritos com o framework JUnit. Para executar os testes, siga estas etapas:

1. Certifique-se de ter o JUnit configurado em seu ambiente de desenvolvimento. Caso contrário, você pode adicionar as dependências necessárias ao seu projeto.
2. Navegue até o diretório de testes: `cd test`
3. Compile os testes: `javac -cp .:junit.jar BlackjackGameTest.java`
4. Execute os testes: `java -cp .:junit.jar org.junit.runner.JUnitCore BlackjackGameTest`

Os testes verificarão se a lógica do jogo está funcionando corretamente, incluindo as regras específicas do blackjack.

## Contribuições

Contribuições são bem-vindas. Para contribuir, siga estas etapas:

1. Faça um fork do projeto.
2. Crie uma nova branch para sua contribuição: `git checkout -b feature/sua-contribuicao`.
3. Faça as alterações necessárias.
4. Envie as alterações: `git push origin feature/sua-contribuicao`.
5. Abra um pull request.

## Licença

Este projeto é distribuído sob a licença MIT. Consulte o arquivo `LICENSE` para obter mais informações.

---

Divirta-se jogando blackjack e contribuindo para este projeto!

## Contato

Se você tiver alguma dúvida ou precisar de assistência, entre em contato em [liedson.b9@gmail.com](mailto:liedson.b9@gmail.com).
