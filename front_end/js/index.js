const url = "http://127.0.0.1:8080";

const inputEmail = document.querySelector("#email");
const inputStatus = document.querySelector("#status");
const botaoCadastrarEmail = document.querySelector("#cadastrarEmail");
const botaoAtualizarStatus = document.querySelector("#atualizarStatus");

const fade = document.querySelector("#fade");
const modal = document.querySelector("#modal");
const modalp = document.querySelector("#modalp");

botaoCadastrarEmail.onclick = async () => {
    if (inputEmail.value === "") {
        mostrarModal("Preencha o campo de email!");
    } else {
        let email = {
            email: inputEmail.value,
            enviar: inputEmail.checked
        }

        let init = {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(email)
        }

        await fetch(url + "/email/cadastrar", init).then((result) => {
            switch (result.status) {
                case 400: // 400 badrequest
                    console.log(result.status + ": Requisição ruim!");
                    mostrarModal("Requisição ruim!");
                    break;
                case 201: // 201 created
                    console.log(result.status + ": Intem criado com sucesso!");
                    mostrarModal("Email cadastrado com sucesso!");
                    break;
                case 200: // 200 ok
                    console.log(result.status + ": Ok");
                    mostrarModal("Email ja cadastrado");
                    break;
                default:
                    throw new Error(result.status);
            }
        }).catch((error) => {
            console.log(error);
            mostrarModal("Falha na conexão com o servidor!");
        });
    }
}

botaoAtualizarStatus.onclick = async () => {
    if (inputEmail.value === "") {
        mostrarModal("Preencha o campo de email!");
    } else {
        let email = {
            email: inputEmail.value,
            enviar: inputStatus.checked
        }

        let init = {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(email)
        }

        await fetch(url + "/email/atualizar", init).then((result) => {
            switch (result.status) {
                case 404: // 404 notfound
                    console.log(result.status + ": Item não encontrado!");
                    mostrarModal("Email não encontrado!");
                    break;
                case 400: // 400 badrequest
                    console.log(result.status + ": Requisição ruim!");
                    mostrarModal("Requisição ruim!");
                    break;
                case 200: // 200 ok
                    console.log(result.status + ": Ok");
                    mostrarModal("Email atualizado");
                    break;
                default:
                    throw new Error(result.status);
            }
        }).catch((error) => {
            console.log(error);
            mostrarModal("Falha na conexão com o servidor!");
        });
    }
}

function mostrarModal(mensagem) {
    modalp.innerHTML = mensagem;

    fade.classList.add("fade");
    modal.classList.add("modal");
    modalp.classList.add("modalp");

    fade.classList.remove("hide");
    modal.classList.remove("hide");
    modalp.classList.remove("hide");

    setTimeout(() => {
        fade.classList.remove("fade");
        modal.classList.remove("modal");
        modalp.classList.remove("modalp");

        fade.classList.add("hide");
        modal.classList.add("hide");
        modalp.classList.add("hide");
        modalp.innerHTML = "";
    }, 2000);
}