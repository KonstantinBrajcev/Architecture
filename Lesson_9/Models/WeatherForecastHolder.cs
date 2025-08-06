using static System.Runtime.InteropServices.JavaScript.JSType;
using System.Collections.Concurrent;

namespace MyForstWebApplication.Models
{
    /// <summary>
    /// Объект на базе класса WeatherForecastHolder, будет хранить список показателей
    /// температуры
    /// </summary>
    public class WeatherForecastHolder
    {
        private readonly ConcurrentDictionary<DateTime, WeatherForecast> _values;
        // Коллекция для хранения показателей температуры
        // private List<WeatherForecast> _values;

        public WeatherForecastHolder()
        {
            // Инициализирую коллекцию для хранения показателей температуры
            _values = new ConcurrentDictionary<DateTime, WeatherForecast>();
        }

        /// <summary>
        /// Добавить новый показатель температуры
        /// </summary>
        /// <param name="dateTime">Дата фиксации показателя температуры</param>
        /// <param name="temperatureC">Показатель температуры</param>
        public void Add(DateTime dateTime, int temperatureC)
        {
            if (_values.ContainsKey(dateTime))
                throw new ArgumentException("Прогноз на эту дату уже существует");

            var forecast = new WeatherForecast
            {
                Date = dateTime,
                TemperatureC = temperatureC
            };

            _values.TryAdd(dateTime, forecast);
        }

        /// <summary>
        /// Обновить показатель температуры
        /// </summary>
        /// <param name="date">Дата фиксации показания температуры</param>
        /// <param name="temperatureC">Новый показатель температуры</param>
        /// <returns>Результат выполнения операции</returns>
        public bool Update(DateTime date, int temperatureC)
        {
            if (!_values.TryGetValue(date, out var forecast))
                return false;

            forecast.TemperatureC = temperatureC;
            return true;
        }

        /// <summary>
        /// Получить показатели температуры за временной период
        /// </summary>
        /// <param name="dateFrom">Начальная дата</param>
        /// <param name="dateTo">Конечная дата</param>
        /// <returns>Коллекция показателей температуры</returns>
        public List<WeatherForecast> Get(DateTime dateFrom, DateTime dateTo)
        {
            return _values.Values
                .Where(x => x.Date >= dateFrom && x.Date <= dateTo)
                .OrderBy(x => x.Date)
                .ToList();
        }

        /// <summary>
        /// Удалить показатель температуты на дату
        /// </summary>
        /// <param name="date">Дата фиксации показателя температуры</param>
        /// <returns>Результат выполнения операции</returns>
        public bool Delete(DateTime date)
        {
            return _values.TryRemove(date, out _);
        }
    }
}