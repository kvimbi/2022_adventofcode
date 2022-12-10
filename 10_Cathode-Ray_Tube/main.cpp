#include <iostream>
#include <fstream>
#include <string>

void draw_pixel(int cycle, int reg)
{
  int tube_pos = cycle % 40;
  if (abs(reg - tube_pos) <= 1)
  {
    std::cout << "#";
  }
  else
  {
    std::cout << " ";
  }

  if ((cycle + 1) % 40 == 0)
  {
    std::cout << "\n";
  }
}

int main()
{
  int index = 0;
  int reg = 1;
  int addx = 0;
  int total = 0;
  int target_cycle = 20;

  std::fstream newfile;
  newfile.open("input.txt", std::ios::in); // open a file to perform read operation using file object
  if (newfile.is_open())
  {
    std::string tp;
    while (getline(newfile, tp))
    {
      draw_pixel(index, reg);

      if (0 == std::string("noop").compare(tp))
      {
        // ¯\_(ツ)_/¯
      }
      else if (0 == std::string("addx").compare(tp.substr(0, 4)))
      {
        addx = std::stoi(tp.substr(5));

        ++index;
        draw_pixel(index, reg);
        if (index == target_cycle)
        {
          total += reg * index;
          target_cycle += 40;
        }
        tp = "(delayed)";
      }

      ++index;
      if (index == target_cycle)
      {
        total += reg * index;
        target_cycle += 40;
      }
      reg += addx;
      addx = 0;
    }
    newfile.close();
  }
  std::cout << "Signal strenght: " << total;
  return 0;
}
