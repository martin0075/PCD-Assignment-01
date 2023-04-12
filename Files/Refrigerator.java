class Refrigerator {

     private final Item[][] normalShelfs;
     private final Item[][] freezingShelfs;

     public Refrigerator(final int normalShelfs, final int itemPerNormalShelfs, final int freezingShelfs, final int itemPerFreezingShelfs) {
          this.normalShelfs = new Item[normalShelfs][itemPerNormalShelfs];
          this.freezingShelfs = new Item[freezingShelfs][itemPerFreezingShelfs];
     }

     public Item[][] getNormalShelfs() {
          return normalShelfs;
     }

     public Item[][] getFreezingShelfs() {
          return freezingShelfs;
     }

     private void putItemInSlot(final Item item, final int shelf, final int slot) {
          if (item.isFreeze()) {
               if (shelf < freezingShelfs.length && slot < freezingShelfs[shelf].length) {
                    if (freezingShelfs[shelf][slot] != null) {
                         freezingShelfs[shelf][slot] = item;
                    }
               }

          } else {
               if (shelf < normalShelfs.length && slot < normalShelfs[shelf].length) {
                    if (normalShelfs[shelf][slot] != null) {
                         normalShelfs[shelf][slot] = item;
                    }
               }
          }
     }

     public boolean putItem(final Item item) {
          if (item.isFreeze()) {
               return putFreeSlotFreezingShelfs(item);
          } else {
               return putFreeSlotNormalShelfs(item);
          }
     }

     public boolean putFreeSlotFreezingShelfs(final Item item) {
          boolean putIn = false;
          for (int i = 0; i < freezingShelfs.length; i++) {
               for (int j = 0; j < freezingShelfs[i].length; j++) {
                    if (freezingShelfs[i][j] == null && !putIn) {
                         freezingShelfs[i][j] = item;
                         putIn = true;
                         return putIn;
                    }
               }
          }
          return putIn;
     }

     public boolean putFreeSlotNormalShelfs(final Item item) {
          boolean putIn = false;
          for (int i = 0; i < normalShelfs.length; i++) {
               for (int j = 0; j < normalShelfs[i].length; j++) {
                    if (normalShelfs[i][j] == null && !putIn) {
                         normalShelfs[i][j] = item;
                         putIn = true;
                         return putIn;
                    }
               }
          }
          return putIn;
     }

     public void printNormalShelfs() {
          System.out.println("stampa degli item negli scaffali normali");

          for (int i = 0; i < normalShelfs.length; i++) {
               for (int j = 0; j < normalShelfs[i].length; j++) {
                    if (normalShelfs[i][j] != null) {
                         final String itemInfo = normalShelfs[i][j].toString();
                         System.out.println("scaffale " + i + ", scomparto " + j + " " + itemInfo);
                    } else {
                         System.out.println("scaffale " + i + ", scomparto " + j + " è vuoto");
                    }
               }
          }
     }

     public void printFreezingShelfs() {
          System.out.println("Stampa degli item negli scaffali congelati");

          for (int i = 0; i < freezingShelfs.length; i++) {
               for (int j = 0; j < freezingShelfs[i].length; j++) {
                    if (freezingShelfs[i][j] != null) {
                         final String itemInfo = freezingShelfs[i][j].toString();
                         System.out.println("scaffale " + i + ", scomparto " + j + " " + itemInfo);
                    } else {
                         System.out.println("scaffale " + i + ", scomparto " + j + " è vuoto");
                    }
               }
          }
     }

     public void clean(final int date) {
          for (int i = 0; i < freezingShelfs.length; i++) {
               for (int j = 0; j < freezingShelfs[i].length; j++) {
                    if (freezingShelfs[i][j] != null && date >= freezingShelfs[i][j].getExpireDate()) {
                         System.out.println("L'elemento " + freezingShelfs[i][j].toString()
                                   + " è stato buttato via dal congelatore");
                         freezingShelfs[i][j] = null;

                    }
               }
          }

          for (int i = 0; i < normalShelfs.length; i++) {
               for (int j = 0; j < normalShelfs[i].length; j++) {
                    if (normalShelfs[i][j] != null && date >= normalShelfs[i][j].getExpireDate()) {
                         System.out.println(
                                   "L'elemento " + normalShelfs[i][j].toString() + " è stato buttato via dal frigo");
                         normalShelfs[i][j] = null;

                    }
               }
          }
     }

}