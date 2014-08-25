import leon.lang._
import leon.lang.synthesis._
import leon.annotation._

object List {
  sealed abstract class List
  case class Cons(head: Int, tail: List) extends List
  case object Nil extends List

  def size(l: List) : Int = (l match {
      case Nil => 0
      case Cons(_, t) => 1 + size(t)
  }) ensuring(res => res >= 0)

  def content(l: List): Set[Int] = l match {
    case Nil => Set.empty[Int]
    case Cons(i, t) => Set(i) ++ content(t)
  }

  def abs(i : Int) : Int = {
    if(i < 0) -i else i
  } ensuring(_ >= 0)

  def split1(list: List): (List,List) = {
    choose { (res: (List,List)) => 
      (content(res._1) ++ content(res._2) == content(list))
    }
  }

  def split2(list: List): (List,List) = {
    choose { (res: (List,List)) => 
      (content(res._1) ++ content(res._2) == content(list)) &&
      abs(size(res._1) - size(res._2)) <= 1
    }
  }
  
  def split3(list: List): (List,List) = {
    choose { (res: (List,List)) => 
      (content(res._1) ++ content(res._2) == content(list)) &&
      abs(size(res._1) - size(res._2)) <= 1 &&
      size(res._1)+size(res._2) == size(list)
    }
  }

}
