using ClinicService.Controllers;
using ClinicService.Models;
using ClinicService.Models.Requests;
using ClinicService.Services;
using Microsoft.AspNetCore.Mvc;
using Moq;
using Xunit;

namespace ClinicService.Tests
{
    public class ClientControllerTests
    {
        private ClientController _clientController;
        private Mock<IClientRepository> _mockClientRepository;

        public ClientControllerTests()
        {
            _mockClientRepository = new Mock<IClientRepository>();
            _clientController = new ClientController(_mockClientRepository.Object);
        }

        [Fact]
        public void GetAllClientsTest()
        {
            List<Client> clients = new List<Client>();
            clients.Add(new Client());
            clients.Add(new Client());
            clients.Add(new Client());

            _mockClientRepository
                .Setup(repository => repository.GetAll())
                .Returns(clients);

            var operationReault = _clientController.GetAll();

            Assert.IsType<OkObjectResult>(operationReault.Result);

            var result = (OkObjectResult)operationReault.Result;
            Assert.IsAssignableFrom<List<Client>>(result.Value);

            _mockClientRepository.Verify(repository => repository.GetAll(), Times.AtLeastOnce);
        }

        [Fact]
        public void GetClientByIdTest()
        {
            // [1] Подготовка данных
            List<Client> clients = new List<Client>();
            clients.Add(new Client());
            clients.Add(new Client());
            clients.Add(new Client());
            int clientId = 2;
            _mockClientRepository.Setup(repository => repository.GetById(clientId)).Returns(clients[clientId]);

            // [2] Исполнение тестируемого метода
            var operationResult = _clientController.GetById(clientId);

            // [3] Подготовка эталонного результата и его сравнение c полученным:
            Assert.IsType<OkObjectResult>(operationResult.Result);
            Assert.IsAssignableFrom<Client>(((OkObjectResult)operationResult.Result).Value);
            Assert.Equal(clients[clientId], ((OkObjectResult)operationResult.Result).Value);
            _mockClientRepository.Verify(repository => repository.GetById(clientId), Times.AtLeastOnce);
        }

        public static object[][] CorrectCreateClientData =
        {
            new object[] { "4567", "Иванов", "Иван", "Иванович", new DateTime(1986, 1, 22) },
            new object[] { "3456", "Иванова", "Анна", "Ивановна", new DateTime(1986, 1, 22) },
            new object[] { "3456", "Иванов", "Илья", "Иванович", new DateTime(2018, 1, 22) },
            new object[] { "3457", "Иванова", "Светлана", "Ивановна", new DateTime(1986, 1, 22) },
        };

        [Theory]
        [MemberData(nameof(CorrectCreateClientData))]
        public void CreateClientTest(
            string document, string surName, string firstName, string patronymic, DateTime birthday)
        {
            // [1] Подготовка данных для тестирования
            CreateClientRequest createClientRequest = new CreateClientRequest();
            createClientRequest.Document = document;
            createClientRequest.SurName = surName;
            createClientRequest.FirstName = firstName;
            createClientRequest.Patronymic = patronymic;
            createClientRequest.Birthday = birthday;

            int res = 1;

            _mockClientRepository
                .Setup(repository =>
            repository
                .Create(It.IsNotNull<Client>()))
                .Returns(res);


            // [2] Исполнение тестируемого метода
            var operationResult = _clientController.Create(createClientRequest);

            // [3] Подготовка эталонного результата и его сравнение c полученным
            Assert.IsType<OkObjectResult>(operationResult.Result);

            var result = (OkObjectResult)operationResult.Result;
            Assert.IsAssignableFrom<int>(result.Value);

            _mockClientRepository.Verify(repository =>
            repository.Create(It.IsNotNull<Client>()), Times.AtLeastOnce());
        }

        public static object[][] CorrectUpdateClientData =
        {
            new object[] { 1, "4567", "Иванов", "Иван", "Иванович", new DateTime(1986, 1, 22) },
            new object[] { 2, "3456", "Иванова", "Анна", "Ивановна", new DateTime(1986, 1, 22) },
            new object[] { 3, "3456", "Иванов", "Илья", "Иванович", new DateTime(2018, 1, 22) },
            new object[] { 4, "3457", "Иванова", "Светлана", "Ивановна", new DateTime(1986, 1, 22) },
        };


        [Theory]
        [MemberData(nameof(CorrectUpdateClientData))]
        public void UpdateClientTest(
            int clientId, string document, string surName, string firstName, string patronymic, DateTime birthday)
        {
            // [1] Подготовка данных для тестирования

            UpdateClientRequest updateClientRequest = new UpdateClientRequest();
            updateClientRequest.ClientId = clientId;
            updateClientRequest.Document = document;
            updateClientRequest.SurName = surName;
            updateClientRequest.FirstName = firstName;
            updateClientRequest.Patronymic = patronymic;
            updateClientRequest.Birthday = birthday;

            int res = 1;

            _mockClientRepository
                .Setup(repository => repository.Update(It.IsNotNull<Client>()))
                .Returns(res);


            // [2] Исполнение тестируемого метода
            var operationResult = _clientController.Update(updateClientRequest);

            // [3] Подготовка эталонного результата и его сравнение c полученным
            Assert.IsType<OkObjectResult>(operationResult.Result);

            var result = (OkObjectResult)operationResult.Result;
            Assert.IsAssignableFrom<int>(result.Value);

            _mockClientRepository.Verify(repository =>
            repository.Update(It.IsNotNull<Client>()), Times.AtLeastOnce());
        }


        [Fact]
        public void DeleteClientTest()
        {
            int answer = 1;

            _mockClientRepository
                .Setup(repository => repository.Delete(1)).Returns(answer);

            var operationResult = _clientController.Delete(2);
            Assert.IsType<OkObjectResult>(operationResult.Result);

            var result = (OkObjectResult)operationResult.Result;
            Assert.IsAssignableFrom<int>(result.Value);

            _mockClientRepository.Verify(repository =>
            repository.Delete(2), Times.AtLeastOnce());
        }

        [Fact]
        public void DeleteClientByIdTest()
        {
            // [1] Подготовка данных
            List<Client> clients = new List<Client>();
            clients.Add(new Client());
            clients.Add(new Client());
            clients.Add(new Client());
            int sizeBefore = clients.Count;
            int clientId = 2;

            _mockClientRepository
                .Setup(repository => repository.Delete(clientId)).Returns(clients.Count).Verifiable();

            // [2] Исполнение тестируемого метода
            var operationResult = _clientController.Delete(clientId);

            // [3] Подготовка эталонного результата и его сравнение c полученным
            Assert.IsType<OkObjectResult>(operationResult.Result);
            Assert.IsAssignableFrom<int>(((OkObjectResult)operationResult.Result).Value);

            _mockClientRepository.Verify(repository => repository.Delete(clientId), Times.AtLeastOnce);
        }

    }

}
