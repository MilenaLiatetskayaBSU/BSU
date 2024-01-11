package lab6666;

public abstract class abstract_multicookers {

		protected String name;
		protected int volume;
		protected String color;
		protected int cost;

		public abstract_multicookers(String name, int volume, String color, int cost)
		{
			this.name = name;
			this.volume = volume;
			this.color = color;
			this.cost = cost;
		}


		public abstract_multicookers()
		{
			this.name = null;
			this.volume = 0;
			this.color = null;
			this.cost = 0;
		}

		public abstract int GetCost();
		public abstract int GetVolume();
		public abstract String GetName();
		public abstract String GetColor();

		}

