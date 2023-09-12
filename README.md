<h1>API Challenge NextIdea - Sprint 3</h1>

<h2>A API está online!</h2>
<p>Para acessar basta clicar no link abaixo:</p>
<a href="http://challenge93359.eastus.azurecontainer.io:8080/swagger-ui/index.html#/">API, Clique Aqui.</a>


<h2>Descrição</h2>
<p>O objetivo da aplicação é utilizar o chatGPT para 
aprimorar a expêriencia do usuário na busca e descoberta 
de produtos. O chatGPT irá funcionar como um 
assistente pessoal de compras que irá abstrair os termos 
complexos dos produtos tornando-os de fácil compreensão para
usuários leigos.</p>

<h2>Beneficios</h2>
<ul>
    <li>
        Aprimorar a expêriencia do usuário na busca e descoberta 
        de produtos.
    </li>
    <li>
        Facilitar a utilização para pessoas com deficiência visual.
    </li>
    <li>
        Reconhecimento de voz integrado com uma poderosa Inteligência Artificial (I.A.).
    </li>
</ul>

<h2>Testando Localmente</h2>
<p>Para testar na máquina local, clone o projeto:</p>

```code
git clone https://github.com/HigorA/challenge-sprint3-devops.git
```

<p>Em seguida, abra o <b>application.properties</b> e substitua a URL de conexão do Banco de Dados, por padrão, o <b>application.properties</b> está configurado para receber esses parametros por substituição no <b>Azure Pipeline</b>.</p>

![VariaveisAzure](/images/configurando_pipeline.1.1.PNG)

<p>Na imagem a cima, o aplicação está recebendo a <b>URL, USERNAME e PASSWORD</b> na hora da execução da pipeline, com a intenção de ocultar os dados de acesso que são sensiveis como a senha.</p>
<br/>
<p>Por isso, se for testar a aplicação localmente, lembre-se de configurar corretamente o <b>application.properties</b>, trocando a <b>URL, USUARIO, SENHA e também o DIALETO do banco</b>, caso o banco utilizado não seja o MySQL.</p>

![ApplicationProperties](/images/applicationproperties.PNG)

<p>Após ter configurado a conexão com o banco de dados, execute os scripts sql para popular as tabelas necessárias no banco de dados.</p>
<p>Depois de concluir os passos acima, é possivel testar a API usando o Swagger.</p>

```code
http://localhost:8080/swagger-ui/index.html
```

<h2>Para Testar na Nuvem (Deploy)- Azure</h2>
<h3>ACR</h3>
<p>Antes de começar, será necessário dar um fork neste repositório.</p>
<p>Para realizar o deploy na <b>Azure</b>, antes de tudo é necessario criar um <b>Resource-Group</b>.</p>

![ResourceGroup](/images/criando_rg.PNG)
<br/>
<p></p>
<p>Após o Resource-Group ser criado, é hora de criar o nosso <b>Container Registry - (ACR)</b></p>

![CriandoACR](/images/criando_acr.PNG)

![CriandoACR2](/images/criando_acr.2.PNG)

<p>Depois mandar <b>CRIAR (CREATE)</b>, é possivel que demore alguns minutos para que o deploy do <b>ACR</b> seja concluido.</p>

<p>Quando o deploy for concluido, você verá uma tela semelhante a esta:</p>

![ACRCridado](/images/acr_criado.PNG)

<p>Agora só precisamos entrar no <b>Recurso</b>:</p>

![DentroDoACR](/images/dentro_do_acr.PNG)

<p>Agora vamos configurar as <b>Chaves De Acesso</b> do <b>ACR</b> necessárias para que nossa pipeline possa acessar o ACR mais pra frente.</p>

![ConfigurandoACR](/images/configurando_acesso_do_acr.PNG)

<h3>Criando Pipeline no Azure Pipelines</h3>

<p>Com o <b>ACR</b> já configurado, podemos partir para a criação da pipeline, após clicarmos no botão <b>New Pipeline</b>, selecionamos para usar o <b>Editor Clássico</b>, após isso, dizemos a <b>Origem, Repositório e a Branch</b> do projeto, neste caso, no github, este repositório, na branch Main.</p>
<p>Lembre-se para você fazer o deploy na Azure, você terá que dar um fork neste repositório!</p>

![CriandoPipeline1](/images/criando_pipeline.1.PNG)

<p>Agora nos selecionamos o <b>Template</b>, por ser uma API, estou usando o template do <b>Docker</b>.</p>

![CriandoPipeline2](/images/criando_pipeline.2.PNG)

<p>Neste passo vamos adicionar uma <b>Tarefa</b> que será responsável por configurar nossas variaveis de acesso do banco de dados.</p>

![CriandoPipeline3](/images/criando_pipeline.3.PNG)

<p>A tarefa a ser adicionada se chama <b>Replace Tokens</b>, esta tarefa será responsável por configurar nossas variaveis de acesso do banco de dados.</p>

![CriandoPipeline4](/images/criando_pipeline.4.PNG)

<h3>Configurando Pipeline no Azure Pipelines</h3>

<p>Agora, na tarefa do <b>Replace Tokens</b>, nos campos <b>Display Name</b> e <b>Target</b>, substituimos o <b>.config</b> por <b>.properties</b>.</p>

![ConfigurandoPipeline1](/images/configurando_pipeline.1.PNG)

<p>Agora, colocamos todas as variaveis que serão usadas pelo <b>Replace Tokens</b>.</p>
<p>A string de conexão, o usuario do banco e a senha do banco de dados.</p>

![ConfigurandoPipeline1](/images/configurando_pipeline.1.1.PNG)

<p>Aqui nos selecionamos o <b>Container Registry Type</b>, selecionamos o <b>Azure Container Registry</b>, depois selecionamos a <b>Subscription</b>, depois selecionamos o <b>ACR</b> que havia sido criado, e por ultimo definimos o <b>Nome da Imagem</b>.</p>

![ConfigurandoPipeline1](/images/configurando_pipeline2.PNG)

<p>Repetimos o mesmo que foi feito no passo anterior.</P>

![ConfigurandoPipeline1](/images/configurando_pipeline3.PNG)

<p>Execução da Pipeline</p>

![RodandoPipeline](/images/pipeline_concluida_job.PNG)

<h3>Configurando Release</h3>

<p>Aqui nos selecionamos o <b>Artifact</b> <b>_Challenge-Sprint3</b>.</p>

![ConfigurandoRelease1](/images/criando_release.1.2.PNG)

<p>Aqui nos selecionamos a <b>Pipeline</b> que ja foi criada.</p>

![ConfigurandoRelease1](/images/criando_release.1.3.PNG)

<p>Agora vamos no <b>Stage (DEV)</b>.</p>

![ConfigurandoRelease1](/images/criando_release.1.1.PNG)

<p>No <b>Agent job</b> selecionamos o <b>ubuntu-latest</b> como nosso <b>Agent Specification</b>.</p>

![ConfigurandoRelease1](/images/criando_release.1.4.PNG)

<p>Já no <b>Azure CLI</b>, selecionamos novamente a subscription, dizemos que o tipo de script é <b>Shell</b>, e dizemos que ele será <b>Inline</b> e escrevemos o comando abaixo.</p>

```code
az container create -g $(resourceGroup) --name $(appName) --image $(acrServer.io)/fiap/$(appName):$(buildNumber) --cpu 1 --memory 1 --registry-login-server $(acrServer.io) --registry-username $(acrName) --registry-password $(acrPassword) --ports 8080 --ip-address Public --dns-name-label $(appName)
```

![ConfigurandoRelease1](/images/criando_release.1.5.PNG)

<p>Neste passo, no configuramos as variaveis que serão usadas pelo comando que digitamos no ultimo passo.</p>

![ConfigurandoRelease1](/images/criando_release.1.6.PNG)

