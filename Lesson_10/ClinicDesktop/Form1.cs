using ClinicServiceNamespace;
using System.Linq.Expressions;

namespace ClinicDesktop
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void btnUpdate_Click(object sender, EventArgs e)
        {
            try
            {
                ClinicClient clinicClient = new ClinicClient("http://localhost:5982/", new HttpClient());
                ICollection<Client> clients = clinicClient.ClientGetAllAsync().Result;

                listViewClients.Items.Clear();

                foreach (Client client in clients)
                {
                    ListViewItem item = new ListViewItem
                    {
                        Text = client.ClientId.ToString()
                    };
                    item.SubItems.Add(new ListViewItem.ListViewSubItem()
                    {
                        Text = client.Document
                    });
                    item.SubItems.Add(new ListViewItem.ListViewSubItem()
                    {
                        Text = client.SurName
                    });
                    item.SubItems.Add(new ListViewItem.ListViewSubItem()
                    {
                        Text = client.FirstName
                    });
                    item.SubItems.Add(new ListViewItem.ListViewSubItem()
                    {
                        Text = client.Patronymic
                    });
                    listViewClients.Items.Add(item);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Не удалось подключиться к базе данных! "+ ex.Message, "Ошибка подключения", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }

        }
    }
}
