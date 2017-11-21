import {PedidoProduto} from "./PedidoProduto";

export class Pedidos {
    
      constructor(public orderArray:Array<PedidoProduto>, public mesa:number, public id:number) {
      }
    }