local utils = require "utils"

local sum_of_powers = 0

local lines = utils.read_lines "CubeConundrum.txt"
if lines == nil then
  return
end

for _, line in ipairs(lines) do
  local sets_str = utils.split(line, ":")[2]
  local sets = utils.split(sets_str, ";")
  local min_number_of_colors = {
    red = 0,
    green = 0,
    blue = 0,
  }

  for _, set in ipairs(sets) do
    local cubes = utils.split(set, ",")

    for _, cube in ipairs(cubes) do
      -- data[1] amount of the color data[2] color name
      local data = utils.split(cube, " ")

      if min_number_of_colors[data[2]] < tonumber(data[1]) then
        min_number_of_colors[data[2]] = tonumber(data[1])
      end
    end
  end

  local power = min_number_of_colors["red"] * min_number_of_colors["green"] * min_number_of_colors["blue"]
  sum_of_powers = sum_of_powers + power
end

print(sum_of_powers)
