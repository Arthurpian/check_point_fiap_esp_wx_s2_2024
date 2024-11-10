# Relatório de Desenvolvimento da API de Similaridade Gênica 
**Integrantes**:
Márcio Gastaldi - RM98811
Arthur Bessa Pian - RM99215
Davi Desenzi - RM550849
**Disciplina**: Engenharia de Software 

## Sumário
- [Introdução](#introducao)
- [Checkpoint 1 - Compressão de Dados Gênicos](#checkpoint-1-compressao-de-dados-genicos)
- [Checkpoint 2 - Geração Aleatória de Cadeias de Nucleotídeos](#checkpoint-2-geracao-aleatoria-de-cadeias-de-nucleotideos)
- [Checkpoint 3 - API para Cálculo de Similaridade Gênica](#checkpoint-3-api-para-calculo-de-similaridade-genica)
- [Conclusão](#conclusao)

## Introdução
Neste relatório, detalhamos o desenvolvimento de uma série de projetos relacionados à análise e manipulação de sequências gênicas. Através de três checkpoints, evoluímos de uma compressão de dados para uma geração de sequências aleatórias e, finalmente, uma API que calcula a similaridade entre sequências gênicas utilizando a distância de Hamming. Cada projeto exigiu o desenvolvimento de habilidades específicas, como manipulação de strings, polimorfismo, e criação de endpoints em uma API RESTful.

## Checkpoint 1 - Compressão de Dados Gênicos
### Objetivo
O objetivo do Checkpoint 1 foi desenvolver um programa em Java para realizar a compressão de cadeias de nucleotídeos, usando o algoritmo de Run-Length Encoding (RLE). Esse tipo de compressão é útil para reduzir o espaço de armazenamento de sequências gênicas ao comprimir cadeias de caracteres repetidos.

### Desenvolvimento
- Implementamos um método de leitura de arquivos de texto contendo sequências de nucleotídeos.
- Aplicamos o algoritmo RLE para comprimir as sequências, reduzindo seu tamanho.
- Criamos uma interface textual para o usuário visualizar a taxa de compressão, além de estatísticas gerais do processo.

### Resultados
O sistema foi capaz de processar arquivos com sequências longas, gerando uma saída comprimida com ganhos significativos de espaço, dependendo da repetição de caracteres na sequência original.

## Checkpoint 2 - Geração Aleatória de Cadeias de Nucleotídeos
### Objetivo
O Checkpoint 2 focou em desenvolver um gerador de cadeias de nucleotídeos aleatórias, com base em uma interface que permite diferentes implementações do método de geração. A proposta visava exercitar o uso de interfaces e polimorfismo para criar várias versões do gerador de sequências.

### Desenvolvimento
- Criamos uma interface chamada `NucleotideoRandomGenerator` com o método `generate(int sequenceSize)`.
- Desenvolvemos uma implementação do método `generate`, que aceita um tamanho de sequência e retorna uma cadeia de nucleotídeos aleatória de acordo com o tamanho fornecido.
- Exploramos diferentes formas de gerar essas cadeias, utilizando polimorfismo para que futuras extensões permitam outras estratégias de geração de sequências.

### Resultados
A implementação foi bem-sucedida, e o sistema gerou sequências aleatórias de diferentes tamanhos conforme especificado na entrada. Esse módulo pode ser reutilizado para gerar dados de teste em outros projetos de bioinformática.

## Checkpoint 3 - API para Cálculo de Similaridade Gênica
### Objetivo
O Checkpoint 3 consistiu na criação de uma API RESTful que calcula a similaridade entre duas sequências de nucleotídeos utilizando a distância de Hamming. A API foi projetada para receber duas sequências e retornar a pontuação de similaridade e distância entre elas, possibilitando a avaliação do grau de semelhança genética.

### Desenvolvimento
- Utilizamos Spring Boot para criar a API RESTful, com um único endpoint `POST /v1/alignment/distance`.
- Definimos o input e output da API no formato JSON, conforme o esquema descrito.
- Implementamos um serviço que calcula a distância de Hamming entre duas sequências, contando o número de posições onde os caracteres diferem.
- A API também retorna um escore de similaridade baseado na razão entre a distância de Hamming e o comprimento da sequência.

### Resultados
Após a implementação, a API foi testada com várias entradas e respondeu de forma precisa e eficiente. O endpoint retorna as informações esperadas no formato JSON, incluindo o cálculo de distância e similaridade. Essa API representa uma base sólida para análises de similaridade genética e pode ser expandida para outros métodos de comparação de sequências.

## Conclusão
O desenvolvimento dos três checkpoints proporcionou uma excelente experiência em manipulação e análise de sequências gênicas. Começamos com a compressão de dados, passamos pela geração de sequências e culminamos em uma API de similaridade genética. O projeto possibilitou a aplicação de conceitos de programação orientada a objetos, estruturação de API RESTful e compressão de dados, reforçando o aprendizado de técnicas avançadas de desenvolvimento de software.

Através de um trabalho colaborativo no GitHub, nosso grupo enfrentou desafios de design e implementação, resolvendo conflitos e compartilhando ideias para obter um produto final consistente e de qualidade. O projeto foi entregue de forma pontual e com uma solução funcional e de fácil manutenção.

