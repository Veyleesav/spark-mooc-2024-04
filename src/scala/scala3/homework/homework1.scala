package homework

import scala.util.Random

class BallsExperiment {
  // Коллекция (List), моделирующая урну с шариками (1 - белый шарик, 0 - черный шарик)
  // В урне 3 белых и 3 черных шара. Нужна изменяемая переменная var, чтобы можно было убирать шары из коллекции
  var balls = 1 :: 0 :: 1 :: 0 :: 1 :: 0 :: Nil
  // Отдельно создаем случайность
  val randomOfInstance = new Random()

  // функция вынимает случайный шар из корзины
  def pickDaBall(): Boolean = {
    val indexToPick = randomOfInstance.nextInt(balls.length)
    //возвращать эта функция должна true (если был выбран белый шар) и false (в противном случае)
    var randomPickResult = true
    if (balls(indexToPick)==0) {
      randomPickResult = false
    }
    //Убираем полученный шар из коллекции
    balls =  balls.patch(indexToPick,Nil,1)
    randomPickResult
  }

  //Найдем вероятность  того, что в первом испытании появится черный шар, а во втором — белый.
  def isFirstBlackSecondWhite: Boolean = {
    if (!pickDaBall && pickDaBall) true else false
  }
}

object BallsTest {
  def main(args: Array[String]): Unit = {
    val count = 10000
    //Генерим список с 10 000 инстансами класса BallsExperiment
    val listOfExperiments: List[BallsExperiment] = List.fill(count)(new BallsExperiment)
    //Применяем к каждому из них функцию, которая возвращает на позицию эксперимента true в ситуации, когда первым случайно достали черный шар, а вторым - белый
    val countOfExperiments = listOfExperiments.map(_.isFirstBlackSecondWhite)
    //Считаем все элементы в списке, которые удовлетворяют условию true
    val countOfPositiveExperiments: Float = countOfExperiments.count(_ ==true)
    //Делим полученное количество элементов на общее количество экспериментов, надеемся получить 3/10.
    println(countOfPositiveExperiments / count)
  }
}